import {Link, Outlet} from "react-router-dom";
import "./Layout.css";
import React, {useState} from "react";

export const UserContext = React.createContext(null);


const Layout = () => {
    const [user, setUser] = useState(null);

    return <div className={"Layout"}>
        <UserContext.Provider value={{user: user, setUser: setUser}}>
            <nav>
                <ul>
                    <li>
                        <Link to={"/"}>SnackOverflow</Link>
                    </li>
                    {!user ?
                        (<React.Fragment>
                            <li>
                                <Link to={"/login"}>Login</Link>
                            </li>
                            <li>
                                <Link to={"/register"}>Register</Link>
                            </li>
                        </React.Fragment>) :
                        (<React.Fragment>
                            <li>
                                <Link to={"/add/question"}>Add Question</Link>
                            </li>
                            <li>
                                <Link to={"/"} onClick={() => {setUser(null); sessionStorage.clear()}}>Logout</Link>
                            </li>
                        </React.Fragment>)
                    }
                </ul>
            </nav>
            <Outlet/>
        </UserContext.Provider>
    </div>
}
export default Layout;