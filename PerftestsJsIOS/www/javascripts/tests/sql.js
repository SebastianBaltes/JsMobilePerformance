module('perftestsjs.tests').sql = (function() {

	function startFN() {
		var rnd = new RandomValues();
		log("open db");
		
		var w = new StopWatch();
		var db = window.openDatabase("MyDatabase", "1.0", "DatabaseDemo", 200000);
		var errorCB = function(tx, err) {
			log("Error processing SQL: "+err.message);
		}
		db.transaction(function(tx){
			tx.executeSql("drop table if exists demo_table;");
			tx.executeSql("create table demo_table ( id integer primary key, name text, description text, peak real, counting integer, businessKey text, lastone text);");
		}, errorCB);
		log(w.stop());
		
		log("insert 1000 rows");
		w.start();
		var ids = [];
		db.transaction(function(tx){
			for (var i = 0; i < 1000; i++) {
				var id = rnd.nextLong();
				ids.push(id);
				tx.executeSql("insert into demo_table (id, name, description, peak, counting, businessKey, lastone) values ("
						+ id
						+ ",\""
						+ rnd.nextString(20)
						+ "\",\""
						+ rnd.nextString(255)
						+ "\","
						+ rnd.nextDouble()
						+ ","
						+ rnd.nextLong()
						+ ",\""
						+ rnd.nextTextWord()
						+ "\",\""
						+ rnd.nextTextWord() + "\")");
			}
		}, errorCB, function() {
			log(w.stop());

			log("perform 1000 queries");
			var sum = 0;
			function performNextQuery(n) {
				if (n==0) {
					if (sum==0) {
						log("sum should not be zero");
					}
					log(w.stop());
				} else {
					db.transaction(function(tx){
						var id = rnd.next(ids);
						function querySuccess(tx, results) {
							var row = results.rows.item(0);
							var readId = row['id'];
							if (id!=readId) {
								log("id incorrect");
							}
							sum = sum + row['peak'];
							performNextQuery(n-1);
						}
						tx.executeSql("select * from demo_table where id="+id, [], querySuccess, errorCB);
					}, errorCB);
				}
			}
			
			performNextQuery(1000);
		});

	}

	function stopFN() {
	}

	return {
		start : startFN,
		stop : stopFN,
		name : "Sql"
	}

})();