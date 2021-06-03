


new Vue({
	el: '#app',
	data: {
		//和v-model绑定
		carId: '',
		messages: [],
		page: {
			pn: 1,
			rn: 8,
			total: 100
		},
		dialogVisible: false,
		fullMessage: [],
	},
	methods: {
		// 点击按钮后的操作
		loadMessageByCarId() {
			axios.request({
				url: 'http://localhost:8080/GarageManagement/FindServlet',
				method: 'get',
				params: {
					carId: this.carId, //传参
					pn: this.page.pn,
					rn: this.page.rn
				}
			}).then((res) => {
				this.page.total = res.data.total;
				this.messages = res.data.messages;
				for (var i = 0; i < this.messages.length; i++) {
					var timestamp = new Date(this.messages[i].timeStart);
					this.messages[i].timeStart = timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					if (this.messages[i].timeEnd != 0) {
						timestamp = new Date(this.messages[i].timeEnd);
						this.messages[i].timeEnd = timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					}

				}
			})
		},
		pageChange(num) {
			this.page.pn = num;
			this.loadMessageByCarId();
		},
		delMessage(row) {
			this.$confirm('是否删除消息，是否继续？', '提示', {
				confirmButtonText: '删除',
				cancelButtonText: '取消',
			}).then(() => {
				axios.request({
					url: 'http://localhost:8080/GarageManagement/DeleteServlet',
					params: {
						mid: row.id
					}
				}).then((res) => {
					if (res.data == 1) {
						this.$message({
							message: '删除成功',
							type: 'success'
						});
					} else {
						this.$message({
							message: '删除失败',
							type: 'warning'
						})
					}
					this.loadMessageByCarId()
				})
			}).catch(() => {
				this.$message('已取消删除');
			})
		},
		handleClose(done) {
			this.$confirm('确认关闭？')
				.then(_ => {
					done();
				})
				.catch(_ => { });
		},
		getFullMessage(row) {
			this.dialogVisible = true;
			axios.request({
				url: 'http://localhost:8080/GarageManagement/FindOneServlet',
				method: 'get',
				params: {
					id: row.id
				}
			}).then((res) => {
				this.fullMessage = res.data;
				for (var i = 0; i < this.fullMessage.length; i++) {
					var timestamp = new Date(this.fullMessage[i].timeStart);
					this.fullMessage[i].timeStart = timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					if (this.fullMessage[i].timeEnd != 0) {
						timestamp = new Date(this.fullMessage[i].timeEnd);
						this.fullMessage[i].timeEnd = timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					}
				}
				if (res.data[0].timeEnd != '0') {
					var i = document.getElementById('pay');
					i.style.display = 'none';
				} else {
					var i = document.getElementById('pay');
					i.style.display = 'inline';
				}
			})
		},
		pay() {
			var date = new Date();
			timeEnd = date.getTime();

			var timeStart = this.fullMessage[0].timeStart;
			var timeBetween = timeEnd - Date.parse(timeStart);	//毫秒数
			this.$confirm('总消费: ' + getPay(timeBetween,this.fullMessage[0].style)+'元', '付款', {
				confirmButtonText: '确定',
			}).then(() => {
				this.$message({
					type: 'success',
					message: '付款成功!'
				});
				axios.request({
					url: 'http://localhost:8080/GarageManagement/PayServlet',
					method: 'get',
					params: {
						id: this.fullMessage[0].id,
						timeEnd: timeEnd
					}
				}).then((res) => {
					this.loadMessageByCarId();
				});
				this.dialogVisible = false;
			}).catch(() => {
				this.$message({
					type: 'fail',
					message: '未进行付款!'
				});
			})
		}
	},
	created() {
		this.loadMessageByCarId();
	}
})

function getPay(timeBetween,style) {
	var second = timeBetween / 1000;
	var hour = second / 3600;
	var minute = (second-hour*3600)/60;
	second = second - hour*3600 - minute*60;
	var priceSingle;
	switch (style){
		case 1:priceSingle = 5;break;
		case 2:priceSingle = 7;break;
		case 3:priceSingle = 9;break;
		case 4:priceSingle = 13;break;
		case 5:priceSingle = 7;break;
	}
	var priceHour = priceSingle * hour;
	var priceMinute = 0;
	if(second>=30){
		minute ++ ;
	}

	if(minute>=30){
		priceMinute = priceSingle;
	}else{
		priceMinute = priceSingle / 2;
	}

	return Math.ceil(priceHour+priceMinute);
}
