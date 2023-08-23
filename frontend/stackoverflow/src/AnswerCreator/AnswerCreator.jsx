import { useState } from "react";
import {useNavigate, useParams} from "react-router-dom";
import AnswerForm from "../AnswerForm";
const CreateAnswer = (answer, id) => {
    console.log(answer, id)
    return fetch(`http://localhost:8080/questions/${id}/add-new-answer`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(answer),
    }).then((res) => res.json());
};

const AnswerCreator = () => {
    const navigate = useNavigate();
    const params = useParams();
    const [loading, setLoading] = useState(false);

    const handleCreateAnswer = (answer) => {
        setLoading(true);

        CreateAnswer(answer, params.id)

            .then(() => {
                setLoading(false);
                navigate("/");
            })
    };

    return (
        <AnswerForm
            onCancel={() => navigate("/")}
            disabled={loading}
            onSave={handleCreateAnswer}
        />
    );
};

export default AnswerCreator;
