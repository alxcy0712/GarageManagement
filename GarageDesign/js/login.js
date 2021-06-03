new Vue({
    el: '#app',
    data: {
        loginForm: {
            id: '',
            password: ''
        },
        loginUser:''
    },
    methods: {
        doLogin() {
            let fd = new FormData();
            for (let prop in this.loginForm) {
                fd.append(prop, this.loginForm[prop]);
            }
            if (this.loginForm.id == '') {
                this.$message('请输入账号')
            } else if (this.loginForm.password == '') {
                this.$message('请输入密码')
            } else {
                axios.request({
                    url: 'http://localhost:8080/GarageManagement/LoginServlet',
                    method: 'post',
                    data: fd
                }).then((res) => {
                    console.log(res);
                    if (res.data.user != 'error') {
                        //成功
                        this.$message('登录成功');
                        sessionStorage.setItem('loginUser', JSON.stringify(res.data.user));
                        location.href = "../html/main.html"
                    } else {
                        //失败
                        this.$message('登入失败');
                        this.loginForm.password = '';
                        this.loginForm.id='';
                    }
                }).catch((res) => {
                    this.$message('服务器连接失败');
                })
            }
        }
    }
})