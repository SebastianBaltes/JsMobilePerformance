module('perftestsjs.tests').longscrolllist = (function() {

	function startFN() {
		var ul = $('#LongScrollListPage > ul');
		ul.empty();
		var item = _.template('\
		    <li>\
				<img src="images/img<%=id%>.png"/>\
				<p><strong><%=name%></strong></p>\
		    	<p><%=description%></p>\
	    	</li>');
        var rnd = new RandomValues();
        for (var i = 0; i < 100; i++) {
        	var li = item({
            	id: i,
            	name: rnd.nextTextWithWords(3),
            	description: rnd.nextTextWithWords(20)
            });
        	ul.append(li);
        }
		ul.listview("refresh");
	}

	function stopFN() {
	}

	return {
		start : startFN,
		stop : stopFN,
		name : "LongScrollList"
	}

})();