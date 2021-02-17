new Vue({
    el: '#app',
    data(){
        return {
            find: [],
            underway: [],
            uuid: +new Date()
        }
    },
    created(){
    },
    methods: {
        async start(){
            const {data:{find, underway, ok}} = await axios.get('http://localhost:8080/myserver?uuid=' + this.uuid)
            this.find = find.map(item => {
                return item.map(i => {
                    let arr = new Array(8).fill(-1);
                    arr[i] = 1;
                    return arr;
                })
            });
            this.underway = underway.map(i => {
                let arr = new Array(8).fill(-1);
                arr[i] = 1;
                return arr;
            })
            if(!ok){
                setTimeout(this.start, 200)
            }
        },
        async start2(){
            const {data:{find, underway, ok}} = await axios.get('http://localhost:8080/myserver2?uuid=' + this.uuid)
            this.find = find.map(item => {
                return item.map(i => {
                    let arr = new Array(8).fill(-1);
                    arr[i] = 1;
                    return arr;
                })
            });
            this.underway = underway.map(i => {
                let arr = new Array(8).fill(-1);
                arr[i] = 1;
                return arr;
            })
            if(!ok){
                setTimeout(this.start2, 200)
            }
        },
    }
});