module('perftestsjs.tests').manyobjects = (function() {

	function Address(rnd) {
		this.line = [rnd.nextString(60),rnd.nextString(60),rnd.nextString(60)];
		this.city = rnd.nextString(10);
		this.plz = rnd.nextString(5);
		this.country = rnd.nextString(10);
	}

	function User(rnd) {
		this.id = rnd.nextLong();
		this.name = rnd.nextString(40);
		this.email = rnd.nextString(40);
		this.birthday = rnd.nextDate();
		this.address = new Address(rnd);
	}
	
	function Part(rnd) {
		this.id = rnd.nextLong();
		this.name = rnd.nextString(20);
		this.businessKey = rnd.nextString(10);
		this.description = rnd.nextString(100);
		this.properties = {};
		for (var i = 0; i < 10; i++) {
			this.properties[rnd.nextString(10)]=rnd.nextString(30);
		}
	}
	
	function Item(rnd) {
		this.id = rnd.nextLong();
		this.name = rnd.nextString(50);
		this.businessKey = rnd.nextString(10);
		this.start = rnd.nextDate();
		this.end = rnd.nextDate();
		this.description = rnd.nextString(500);
		this.width = rnd.nextDouble();
		this.height = rnd.nextDouble();
		this.important = rnd.nextBoolean();
		this.parts = [];
		for (var i = 0; i < 10; i++) {
			this.parts.push(new Part(rnd));
		}
	}
	
	function Shop(rnd) {
		this.items = {};
		this.users = {};
		for (var i = 0; i < 200; i++) {
			var item = new Item(rnd);
			this.items[item.businessKey] = item;
		}
		for (var i = 0; i < 200; i++) {
			var user = new User(rnd);
			this.users[user.email] = user;
		}
	}
	
	function startFN() {
		log("create 200 Items x 10 Parts x 10 Properties + 200 Users");
		var w = new StopWatch();
		var rnd = new RandomValues();
		var shop = new Shop(rnd);
		log(w.stop());
	}

	function stopFN() {
	}

	return {
		start : startFN,
		stop : stopFN,
		name : "ManyObjects"
	}

})();