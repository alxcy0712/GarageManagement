

let vue = new Vue({
	el: '#app',
	data: {
		carInfo: {
			carId: '',
			option: null,
			location: ''
		},
		options:[{
			value:1,
			label:'小型车'
		},{
			value:2,
			label:'中型车'
		},{
			value:3,
			label:'大型车'
		},{
			value:4,
			label:'跑车'
		},{
			value:5,
			label:'其他类'
		}]
	},
	methods: {
		// 点击按钮添加后的操作
		add() {
			this.$confirm('是否要添加车牌号：'+this.carInfo.carId+'    停放位置：'+this.carInfo.location, '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				//get提交方式 完整信息不能超过255个字符
				axios
					.request({
						url: 'http://localhost:8080/GarageManagement/AddServlet',
						method: 'get',
						params: this.carInfo
					}).then((res) => {
						switch(res.data){
							case 1:
							this.$message({
								type: 'success',
								message: '成功'
							});
							this.carInfo.carId = '';
							this.carInfo.option = null;
							this.carInfo.location = '';
							break;
							case 2:
							this.$message({
								type: 'error',
								message: '失败！原因：车位被占用'
							});break;
							case -1:
							this.$message({
								type: 'error',
								message: '失败！数据库查询错误'
							});break;
							case -2:
							this.$message({
								type: 'error',
								message: '失败！未填入任何信息'
							});break;
							case 300:
							this.$message({
								type: 'error',
								message: '失败!原因：未输入车牌号'
							});break;
							case 301:
							this.$message({
								type: 'error',
								message: '失败！原因：未选择车辆类型'
							});break;
							case 302:
							this.$message({
								type: 'error',
								message: '失败！原因：未输入车位'
							});break;
							default:
							this.$message({
								type: 'error',
								message: '失败！未知错误'
							});break;
						}
					});
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消添加'
				});
			});
		}
	}
})
