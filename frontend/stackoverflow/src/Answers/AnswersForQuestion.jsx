import {Link, useParams} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import {UserContext} from "../Layout/Layout";

const fetchAnswersForQuestion = (id) => {
    return fetch(`${process.env.BACKEND_URL}/questions/${id}/answers`)
        .then(response => response.json());
}
const AnswersForQuestion = () => {
    const {user} = useContext(UserContext);
    const params = useParams();
    const [questionWithAnswers, setQuestionWithAnswers] = useState(null);
    useEffect(() => {
        fetchAnswersForQuestion(params.id)
            .then(answers => setQuestionWithAnswers(answers));
    }, [])

    if (questionWithAnswers == null) return (<div>LOADING...</div>);
    return (<div className={"AnswersForQuestions"}>
        <h1>{questionWithAnswers.description}</h1>

        <table className={"QuestionsTable"}>
            <thead>
            <tr>
                <th>
                    Text
                </th>
                <th>
                    Date
                </th>
                <th>

                </th>
            </tr>
            </thead>
            <tbody>
            {questionWithAnswers.answers.map(answer => (<tr key={answer.id}>
                <td>
                    {answer.text}
                </td>
                <td>
                    {answer.date}
                </td>
                <td>
                    {answer.username}
                </td>
            </tr>))}
            </tbody>
        </table>
        {user ? <Link to={`/${questionWithAnswers.id}/add/answer`}>Add new Answer</Link> : null}
    </div>)
}
export default AnswersForQuestion;