import "./MainPage.css";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

const fetchQuestions = (sortBy, direction) => {
    return fetch(`http://localhost:8080/questions/all?order_by=${sortBy}&direction=${direction}`)
        .then(response => response.json());
}
const MainPage = () => {
    const [questions, setQuestions] = useState(null);
    const [field, setField] = useState("");
    const [direction, setDirection] = useState("");
    useEffect(() => {
        fetchQuestions()
            .then(questions => {
                setQuestions(questions);
            })
    }, []);
    if (questions == null) return (<div>LOADING...</div>);

    const handleSubmit = (event) => {
event.preventDefault();
        fetchQuestions(field, direction).then(questions => setQuestions(questions));
    }

    return (<div className={"MainPage"}>
        <form onSubmit={(e)=>handleSubmit(e)}>
            <label htmlFor="field">Order by: </label>
            <select name="fields" id="field" onChange={(e) => setField(e.target.value)}>
                <option value="title">title</option>
                <option value="date">date</option>
                <option value="answercount">Answer Count</option>
            </select>
            <select name="directions" id="direction" onChange={(e) => setDirection(e.target.value)}>
                <option value={"DESC"}>descending</option>
                <option value={"ASC"}>ascending</option>
            </select>
            <input type="submit" value="Sort"/>
        </form>

        <table className={"QuestionsTable"}>
            <thead>
            <tr>
                <th>
                    Title
                </th>
                <th>
                    Date
                </th>
                <th>
                    
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
                    {question.username}
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