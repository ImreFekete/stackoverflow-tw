import {Outlet, Link} from "react-router-dom";
import "./Layout.css";

const Layout = () =>{
    return <div className={"Layout"}>
        <nav>
            <ul>
                <li>
                    <Link to={"/"}>SnackOverflow</Link>
                </li>
                <li>
                    <Link to={"/add/question"}>Add Question</Link>
                </li>
            </ul>
        </nav>
        <Outlet />
    </div>
}
export default Layout;