/**
 * Created by LeeKane on 2017/6/4.
 */
let MovieItem=React.createClass({
    getInitialState: function() {
        return {
            movie:{},
        };
    },
    componentDidMount: function() {
        window.fetch('./getMovie',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "name": "加勒比海盗5：死无对证",
            })
        })
            .then(res => res.json())
            .then(data => {
                this.setState({
                   movie:data.movie,
                });
            });

    },

    render(){
        let {actors,comment,imgUrl,name,score} = this.state.movie;
        return(
            <div className="movieItem">
                <div className="movieImgContainer">
                    <img className="movieImg" src={imgUrl}/>
                </div>
                <div className="movieInfo">
                    <p className="movieName">{name}</p>
                    <p className="movieComment">{comment}</p>
                    <p className="movieActors">{actors}</p>
                </div>
                <div className="movieButtonContainer">
                    <div className="movieScore">{`${score} 分`}</div>
                    <div className="movieButton"><a className="waves-effect waves-light btn">购票</a></div>
                </div>
            </div>
        );
    }
});

ReactDOM.render(
    <MovieItem/>,
    document.getElementById('container')
);