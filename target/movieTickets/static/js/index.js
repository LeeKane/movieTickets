/**
 * Created by LeeKane on 2017/6/4.
 */
let movies = [];
window.fetch('./getAllMovies')
    .then(res=> res.json())
    .then(data=>{
        movies=data.movies;
        ReactDOM.render(
            <MovieTable movies={movies}/>,
            document.getElementById('container')
        );
    });

let MovieItem=React.createClass({
    componentDidMount: function() {
        // get a movie by name**
        //
        // window.fetch('./getMovie',{
        //     method: 'POST',
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         "name": "加勒比海盗5：死无对证",
        //     })
        // })
        //     .then(res => res.json())
        //     .then(data => {
        //         this.setState({
        //            movie:data.movie,
        //         });
        //     });
    },

    render(){
        let {movie}=this.props;
        let {actors,comment,imgUrl,name,score,id} = movie;
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
                    <div className="movieButton"><a className="waves-effect waves-light btn" href={`./movieInfo?id=${id}`}>购票</a></div>
                </div>
            </div>
        );
    }
});

let MovieTable=React.createClass({
    componentDidMount: function() {

    },
    render(){
        let rows=[];
        let {movies}=this.props;
        movies.forEach(function(movie){
            rows.push(<MovieItem key={movie.id} movie={movie}/>);
        });
        return(
            <div>
                {rows}
            </div>
        );
    }
});



