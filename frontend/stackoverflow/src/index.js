import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import reportWebVitals from './reportWebVitals';
import './router.css';

import Layout from "./Layout";
import ErrorPage from "./ErrorPage";
import MainPage from "./MainPage";
import AnswersForQuestion from "./Answers";
import QuestionCreator from "./QuestionCreator/QuestionCreator";
import AnswerCreator from "./AnswerCreator";
import Login from './Login/Login';
import Register from './Register/Register';

const index = createBrowserRouter([{
    path: "/", element: <Layout/>, errorElement: <ErrorPage/>, children: [{
        path: "/", element: <MainPage/>
    }, {
        path: "/answers/:id", element: <AnswersForQuestion/>
    }, {
        path: "/login", element: <Login/>
    }, {
        path: "/register", element: <Register/>
    }, {
        path: "/add/question", element: <QuestionCreator/>
    }, {
        path: "/:id/add/answer", element: <AnswerCreator/>
    }]
}]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<React.StrictMode>
    <RouterProvider router={index}/>
</React.StrictMode>);

reportWebVitals();
