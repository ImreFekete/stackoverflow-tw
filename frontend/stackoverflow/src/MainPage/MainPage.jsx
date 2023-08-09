import "./MainPage.css";
import {useState} from "react";
import {Link} from "react-router-dom";
const MainPage = () =>{
    const [questions, setQuestions] = useState(null);

    return (
        <div className={"MainPage"}>
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
                {
                    questions.map(question =>(
                        <tr key = {question.id}>
                            <th>
                                <Link to={""}>{question.name}</Link>
                            </th>
                            <th>
                                {question.date}
                            </th>
                            <th>
                                {question.answerCount}
                            </th>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    )
}
export default MainPage;