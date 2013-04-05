function StopWatch() {
	this.start();
}
StopWatch.prototype.start = function(o) {
	this.t0 = new Date().getTime();
};
StopWatch.prototype.stop = function(o) {
	var t1 = new Date().getTime();
	return "time: \t"+(t1-this.t0)+" ms";
};
