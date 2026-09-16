const base = {
    get() {
        return {
            url: "http://10.70.163.190:8080/springbootzkgw/",
            name: "springbootzkgw",
            // 退出到首页链接
            indexUrl: 'http://10.40.92.160:8080/springbootzkgw/front/index.html'
        };
    },
    getProjectName() {
        return {
            projectName: "校园掌上购物"
        }
    }
}
export default base
