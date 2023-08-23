import { useState } from "react";
import { useNavigate } from "react-router-dom";
import QuestionForm from "../QuestionForm/QuestionForm";
const createQuestion = (question) => {
    return fetch("http://localhost:8080/questions/", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(question),
    }).then((res) => res.json());
};

const QuestionCreator = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const handleCreateQuestion = (question) => {
        setLoading(true);

        createQuestion(question)
            .then(() => {
                setLoading(false);
                navigate("/");
            })
    };

    return (
        <QuestionForm
            onCancel={() => navigate("/")}
            disabled={loading}
            onSave={handleCreateQuestion}
        />
    );
};

export default QuestionCreator;
