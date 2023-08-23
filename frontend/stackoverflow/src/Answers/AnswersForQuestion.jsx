import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

const fetchAnswersForQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}/answers`)
        .then(response => response.json());
}
const AnswersForQuestion = () => {
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
                    </tr>))}
                    </tbody>
                </table>
            <Link to={`/${questionWithAnswers.id}/add/answer`}>Add new Answer</Link>
            </div>)
}
export default AnswersForQuestion;