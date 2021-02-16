new Vue({
    el: '#app',
    data(){
        return {
            map: [],
            messageMap: [],
            row: 16,
            cell: 38,
            num: 100,
            start1Strategy: [
                {fun: (x, y) => [x + 1, y], message: '↓'},
                {fun: (x, y) => [x, y + 1], message: '→'},
                {fun: (x, y) => [x - 1, y], message: '↑'},
                {fun: (x, y) => [x, y - 1], message: '←'}
            ],
            start2Strategy: [
                {fun: (x, y) => [x - 1, y], message: '↑'},
                {fun: (x, y) => [x + 1, y], message: '↓'},
                {fun: (x, y) => [x, y - 1], message: '←'},
                {fun: (x, y) => [x, y + 1], message: '→'}
            ],
            start3Strategy: [
                {fun: (x, y) => [x - 1, y], message: '↑'},
                {fun: (x, y) => [x, y + 1], message: '→'},
                {fun: (x, y) => [x + 1, y], message: '↓'},
                {fun: (x, y) => [x, y - 1], message: '←'}
            ]
        }
    },
    created(){
        this.initWall();
    },
    methods: {
        initWall(){
            this.reset();
        },
        sleep(n){
            let start = new Date();
            //  console.log('休眠前：' + start);
            while (true) {
                if (+new Date() - start > n) {
                    break;
                }
            }
        },
        setWay(i, j, strategy){
            if(i === this.row -2 && j === this.cell - 2){
                this.map[i][j] = 2;
                this.map = this.map.deepClone();
                return true;
            }

            if(this.map[this.row -2][this.cell - 2] === 2){
                return true;
            } else {
                //如果当前这个节点能走通
                if(this.map[i][j] === 0){
                    //假定可以走通
                    this.map[i][j] = 2;
                    this.map = this.map.deepClone();
                    for (let strategyElement of strategy) {
                        if(this.setWay(...strategyElement.fun(i, j), strategy)){
                            this.messageMap[i][j] = strategyElement.message;
                            return true;
                        }
                    }
                    this.map[i][j] = 3;
                    return false;
                }else {
                    return false;
                }
            }

        },
        setWay2(i, j, map){
            console.log(i, j)
            if(i === this.row -2 && j === this.cell - 2){
                map[i][j] = 2;
                return {map: map, step: 1};
            }

            if(map[this.row -2][this.cell - 2] === 2){
                return {map: map, step: 1};
            } else {
                //如果当前这个节点能走通
                if(map[i][j] === 0){
                    //假定可以走通
                    map[i][j] = 2;
                    let xia = this.setWay2(i + 1, j, map.deepClone())
                    let you = this.setWay2(i , j + 1, map.deepClone())
                    let shang = this.setWay2(i - 1, j, map.deepClone())
                    let zuo = this.setWay2(i , j - 1, map.deepClone())
                    let min = [xia, you, shang, zuo].filter(item => item.step !== -1).map(item => item.step).getMin();
                    if(min === -1){
                        map[i][j] = 3;
                        return {map: map, step: -1};
                    }else {
                        const res = [xia, you, shang, zuo].filter(item => item.step === min)[0];
                        res.step += 1;
                        return res;
                    }
                }else {
                    return {map: map, step: -1};
                }
            }

        },
        start(type){
            this.clear();
            const bool = this.setWay(1, 1, this[`start${type}Strategy`])
            if(!bool){
                alert('无法达到目的地！')
            }
        },
        start2(){
            this.clear();
            const obj = this.setWay2(1, 1, this.map.deepClone())
            this.map = obj.map;
            alert('最短路径：' + obj.step)
            console.log(obj)
        },
        clear(){
          for(let x in this.map){
              for(let y in this.map[x]){
                  if(this.map[x][y] === 2 || this.map[x][y] === 3){
                      this.map[x][y] = 0;
                  }
              }
          }
        }
        ,
        reset(){
            const map = (
                 () => {
                    let arr = new Array(this.row).fill([]);
                    for (let i in arr) {
                        arr[i] = new Array(this.cell).fill(0);
                    }
                    return arr;
                }
            )()
            this.messageMap = (
                () => {
                    let arr = new Array(this.row).fill([]);
                    for (let i in arr) {
                        arr[i] = new Array(this.cell).fill('');
                    }
                    return arr;
                }
            )()
            for (let i = 0; i < this.cell; i++) {
                map[0][i] = 1;
                map[this.row - 1][i] = 1;
            }
            for (let i = 0; i < this.row; i++) {
                map[i][0] = 1;
                map[i][this.cell - 1] = 1;
            }
            let min = Math.min(this.row * this.cell - (this.row * 2 + (this.cell - 2) * 2) - 2, this.num)
            for(let i = 0; i < min; i++){
                const x = Number.parseInt(Math.random() * this.row)
                const y = Number.parseInt(Math.random() * this.cell)
                if(map[x][y] === 1){
                    i--;
                    continue
                }
                if((x === 1 && y === 1) || (x === this.row - 2 && y === this.cell - 2)){
                    i--;
                    continue;
                }
                map[x][y] = 1;
            }
            this.map = map;
        }
    }
});