new Vue({
	el:'#app'	
})

function clickWelcome(){
	var iframe = document.getElementById('right');
	iframe.src = 'welcome.html';
	var title = document.getElementById('title');
	title.innerHTML = '机动车车辆车库管理系统';
}
function clickNew(){
	var iframe = document.getElementById('right');
	iframe.src = 'add.html';
	var title = document.getElementById('title');
	title.innerHTML = '机动车车辆车库管理系统——————添加新数据';
}
function clickOperation(){
	var iframe = document.getElementById('right');
	iframe.src = 'search.html';
	var title = document.getElementById('title');
	title.innerHTML = '机动车车辆车库管理系统——————操作';
}