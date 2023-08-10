import { useState } from "react";

const QuestionForm = ({ onSave, disabled, employee: question, onCancel }) => {
    const [title, setTitle] = useState(question?.name ?? "");
    const [text, setText] = useState(question?.text ?? "");

    const onSubmit = (e) => {
        e.preventDefault();

        if (question) {
            return onSave({
                ...question,
                title: title,
                text: text
            });
        }

        return onSave({
            title: title,
            text: text
        });
    };

    return (
        <form className="EmployeeForm" onSubmit={onSubmit}>
            <div className="control">
                <label htmlFor="name">Name:</label>
                <input
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    name="title"
                    id="title"
                />
            </div>

            <div className="control">
                <label htmlFor="description">Description:</label>
                <input
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    name="description"
                    id="description"
                />
            </div>
            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {question ? "Update Question" : "Create Question"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default QuestionForm;
