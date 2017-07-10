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
        return(
            <div className="movieItem">
                <div className="movieImgContainer">
                    <img className="movieImg" src={movie.img}/>
                </div>
                <div className="movieInfo">
                    <p className="movieName">{movie.name} <span className="movieVer">{movie.ver}</span></p>
                    <p className="movieComment">{movie.scm}</p>
                    <p className="movieActors">{movie.star} <span className="movieRt">{movie.showdate}</span></p>
                </div>
                <div className="movieButtonContainer">
                    <div className="movieScore"><span className="scText">{movie.scoremaoyan}</span> 分</div>
                    <div className="movieButton"><a className="waves-effect waves-light btn" href={`./movieInfo?id=${movie.name}`}>详情</a></div>
                </div>
            </div>
        );
    }
});

let MovieTable=React.createClass({
    getInitialState: function() {
        return {
            filterText: '',
        };
    },
    handleFilterChange:function(text){
        this.setState({
            filterText:text
        });
    },
    componentDidMount: function() {

    },
    render(){
        let rows=[];
        let {movies}=this.props;
        let {filterText}=this.state;
        movies.forEach(function(movie){
            if(movie.name.indexOf(filterText) === -1)
            {}
            else {
                rows.push(<MovieItem key={movie.name} movie={movie}/>);
            }
        });
        return(
            <div>
                <SearchNav filterText={filterText} handleFilterChange={this.handleFilterChange}/>
                {rows}
            </div>
        );
    }
});


let SearchNav=React.createClass({
    handleChange:function(){
      let {handleFilterChange}  = this.props;
        handleFilterChange(this.refs.filterTextInput.value);
    },
    render(){
        let {filterText} =this.props;
        return(
            <nav className="white" role="navigation">
                <div className="nav-wrapper container">
                    <div className="searchContainer">
                        <div className="iconContainer">
                            <i className="material-icons searchIcon">search</i>
                        </div>
                        <div className="inputContainer">
                            <input placeholder="搜电影" id="first_name" type="text" className="searchInput" style={{height: '2rem',color: '#666'}}
                                   value={filterText}
                                   ref="filterTextInput"
                                   onChange={this.handleChange}/>
                        </div>
                    </div>
                </div>
            </nav>
        );
    }
});

