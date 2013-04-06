//
//  SqlViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "SqlViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"
#import <sqlite3.h>

@interface SqlViewController ()

@end

@implementation SqlViewController

RandomValues* rnd;
NSString* text;

NSString *databasePath;
sqlite3 *contactDB;

- (void)performAction
{
    rnd = [RandomValues create];
    StopWatch *w = [StopWatch create];

    [self log:@"open db"];
    
    NSString *docsDir;
    NSArray *dirPaths;
    
    // Get the documents directory
    dirPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    docsDir = dirPaths[0];
    
    // Build the path to the database file
    databasePath = [[NSString alloc] initWithString: [docsDir stringByAppendingPathComponent:@"dummy_db.db"]];

    const char *dbpath = [databasePath UTF8String];
    
    if (sqlite3_open(dbpath, &contactDB) != SQLITE_OK) {
        [self log:@"can not open db!"];
        return;
    }
    
    [self executeStmt:@"CREATE TABLE IF NOT EXISTS demo_table ( id integer primary key, name text, description text, peak real, counting integer, businessKey text, lastone text)"];

    [self log:[w stop]];
    [w start];
        
    [self log:@"delete all previous rows"];
        
    [self executeStmt:@"delete from demo_table"];

    [self log:[w stop]];
    [w start];

    [self log:@"insert 1000 rows"];
    
    [self executeStmt:@"BEGIN EXCLUSIVE TRANSACTION"];
    
    NSMutableArray *ids = [NSMutableArray array];
    
    for (int i = 0; i < 1000; i++) {
        NSString *id_ = [NSString stringWithFormat:@"%d",[rnd nextInt]];
        [ids addObject:id_];

        [self executeStmt:[NSString stringWithFormat:@"insert into demo_table (id, name, description, peak, counting, businessKey, lastone) values (%@,\"%@\",\"%@\",%f,%d,\"%@\",\"%@\")",
                           id_,[rnd nextString:20],[rnd nextString:255],[rnd nextDouble],[rnd nextInt],[rnd nextTextWord],[rnd nextTextWord]]];
    }

    [self executeStmt:@"COMMIT TRANSACTION"];

    [self log:[w stop]];

    [self log:@"perform 1000 queries"];
    [w start];

    double sum = 0;
    for (int i = 0; i < 1000; i++) {
        NSString *id_ = [rnd nextFromArray:ids];
        NSString *querySQL = [NSString stringWithFormat:
                              @"select * from demo_table where id=%@",
                              id_];
        
        const char *query_stmt = [querySQL UTF8String];
        sqlite3_stmt *statement;
        if (sqlite3_prepare_v2(contactDB,
                               query_stmt, -1, &statement, NULL) == SQLITE_OK) {
            if (sqlite3_step(statement) == SQLITE_ROW) {
                
                NSString *id2=[NSString stringWithFormat:@"%d",sqlite3_column_int(statement, 0)];

                if (![id_ isEqualToString:id2]) {
                    [self log:@"id incorrect"];
                }
                
                sum+=sqlite3_column_double(statement, 4);
    
            }
            sqlite3_finalize(statement);
        }
    }
    
    if (sum==0) {
        [self log:@"sum should not be zero"];
    }

    [self log:[w stop]];
 
    sqlite3_close(contactDB);

}

-(void)executeStmt:(NSString *)string {
    const char *sql_stmt = [string UTF8String];
    char *errMsg;
    int result = sqlite3_exec(contactDB, sql_stmt, NULL, NULL, &errMsg);
    if (result != SQLITE_OK) {
        [self log:[NSString stringWithFormat:@"DB ERROR msg=%s, result=%d",errMsg,result]];
    }
}

@end
