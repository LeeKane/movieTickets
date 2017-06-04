/**
 * Created by LeeKane on 2017/6/4.
 */
let Hello=React.createClass({
    render(){
        return(
            <div>
                <h1>Hello</h1>
            </div>
        );
    }
});

ReactDOM.render(
    <Hello/>,
    document.getElementById('container')
);