import "./MainPage.css";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

const fetchQuestions = () => {
    return fetch("http://localhost:8080/questions/all")
        .then(response => response.json());
}
const MainPage = () => {
    const [questions, setQuestions] = useState(null);
    useEffect(() => {
        fetchQuestions()
            .then(questions => {
                setQuestions(questions);
            })
    }, []);
    if (questions == null) return (<div>LOADING...</div>);
    return (<div className={"MainPage"}>
            <table className={"QuestionsTable"}>
                <thead>
                <tr>
                    <th>
                        Question
                    </th>
                    <th>
                        Date
                    </th>
                    <th>
                        Answer Count
                    </th>
                </tr>
                </thead>
                <tbody>
                {questions.map(question => (<tr key={question.id}>
                    <td>
                        <Link to={`/answers/${question.id}`}>{question.title}</Link>
                    </td>
                    <td>
                        {question.created}
                    </td>
                    <td>
                        {question.answerCount}
                    </td>
                </tr>))}
                </tbody>
            </table>
        </div>)
}
export default MainPage;