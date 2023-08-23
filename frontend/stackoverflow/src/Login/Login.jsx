import { useState } from "react";
import {useNavigate, useParams} from "react-router-dom";



const Login = () => {
    const navigate = useNavigate();
    const params = useParams();
    const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [loginPopup, setLoginPopup] = useState(false);
  const [invalidPopup, setInvalidPopup] = useState(false);

  const URL = 'http://localhost:8080/users/login';
  const submitLogin = async () => {
    const res = await fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: userName,
        password: password,
      }),
    });
    const data = await res.json();
    if (res.ok) {
      setLoginPopup(true);
      setUserName(data.username);
      sessionStorage.setItem("userId", data);
      setTimeout(() => {
        setLoginPopup(false);
        navigate("/")
      }, 1500);
    } else {
      setInvalidPopup(true);
      setTimeout(() => {
        setInvalidPopup(false);
      }, 1000);
    }
  };

    return (
        <div className="loginContainer">
          <form
            className="loginForm"
            onSubmit={(e) => {
              e.preventDefault();
              submitLogin();
            }}
          >
            <div className="loginTitle">
              <p>Please sign in!</p>
            </div>
            <div className="userInput">
              <label>Username:</label>
              <input type="text" onChange={(e) => setUserName(e.target.value)} />
            </div>
            <div className="userInput">
              <label>Password:</label>
              <input type="password" onChange={(e) => setPassword(e.target.value)} />
            </div>
            <div className="loginButtons">
              <button type="submit">Sign in!</button>
              <button onClick={() => navigate("/")}>Cancel</button>
            </div> 
          </form>
          {loginPopup ? (
            <div className="popup-container">
              <div className="popup-body">
                <h1>Succesfully logged in</h1>
              </div>
            </div>
          ) : null}
          {invalidPopup ? (
            <div className="popup-container">
              <div className="popup-body">
                <h1>Invalid username or password</h1>
              </div>
            </div>
          ) : null}
        </div>
      );
};

export default Login;