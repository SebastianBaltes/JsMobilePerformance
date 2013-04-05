var logElement = $('#log');

var allLogs = "";

function log(s) {
	logElement.html(logElement.html()+s+"<br/>");
	permLog(s);
}

function permLog(s) {
	console.log(s);
	allLogs += s + "\n";
}

function sendMail() {
	var subject = "Performance Test: android/javascript/"+device.name;
	
	var text = "";
	text+="plattform: "+device.platform+"\n";
	text+="language: javascript\n";
	text+="os version: "+device.version+"\n";
	text+="model: "+device.name+"\n";
	text+="\n";
	text+=allLogs;
	
	var email = "baltes@objectcode.de";
	
	window.plugins.emailComposer.showEmailComposerWithCallback(null,subject,text,[email],[],[],false,[]);
}


$(function(){
	var nullModule = {start:function(){},stop:function(){},name:'Main'};
	$("div[data-role='page']").live('pageshow',function(event, ui){
		function getModule(page) {
			var moduleName = page.attr('data-testmodule');
			if (typeof moduleName==='undefined') {
				return nullModule;
			}
			var module = eval(moduleName);
			if (typeof module==='undefined') {
				return nullModule;
			}
			return module;
		}
		var prevModule = getModule(ui.prevPage);
		var newModule = getModule($(this));
		console.log("page transition "+prevModule.name+" -> "+newModule.name);
		prevModule.stop();
		if (newModule!==nullModule) {
			$.mobile.showPageLoadingMsg("a", newModule.name);
		}
		
		if (newModule!==nullModule) {
			permLog("----------------------");
			permLog(newModule.name);
			permLog("----------------------");
		}
		
		logElement = $('#log',$(this));
		logElement.empty();
		setTimeout(function(){
			newModule.start();
			if (newModule!==nullModule) {
				$.mobile.hidePageLoadingMsg();
			}
		},1);
	});
	$('#SendMail').click(function() {
		sendMail();
	});
});

