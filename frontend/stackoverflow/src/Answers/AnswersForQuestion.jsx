import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

const fetchAnswersForQuestion = (id) => {
    return fetch("http://localhost:8080/questions/:id/answers")
        .then(response => response.json());
}
const AnswersForQuestion = () => {
    const params = useParams();
    const [answersForQuestion, setAnswersForQuestion] = useState(null);

    useEffect(() => {
        fetchAnswersForQuestion(params.id)
            .then(answers => setAnswersForQuestion(answers));
    })

    if (answersForQuestion == null) return (<div>LOADING...</div>);
    return (<div className={"AnswersForQuestions"}>
        {answersForQuestion.map(question => (<div key={question.id}>
                <h1>{question.description}</h1>

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
                    {question.answers.map(answer => (<tr key={answer.id}>
                        <td>
                            {answer.text}
                        </td>
                        <td>
                            {answer.date}
                        </td>
                    </tr>))}
                    </tbody>
                </table>
            </div>))}
    </div>);
}
export default AnswersForQuestion;