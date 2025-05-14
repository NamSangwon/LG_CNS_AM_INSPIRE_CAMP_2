import { Link } from "react-router-dom";

const Nav = () => {

    
    return (
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <div class="container-fluid">
                <ul class="navbar-nav">
                    <li class="nav-item">
                         {/* 
                            [SPA] (Single Page Application)
                            React에서의 a 태그 대체 
                                -> 페이지를 새로 로딩하는 것이 아닌 
                                    컴포넌트 만을 변경

                            * button을 사용하는 경우에는 useNavigate() 사용!
                        */}
                        <Link class="nav-link active" to="/board/list">게시판</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link" to="#">Link</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link" to="#">Link</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link disabled" to="#">Disabled</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

export default Nav;