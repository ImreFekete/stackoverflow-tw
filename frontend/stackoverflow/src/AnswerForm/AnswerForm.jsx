import { useState } from "react";

const AnswerForm = ({ onSave, disabled, answer, onCancel }) => {
    const [text, setText] = useState(answer?.text ?? "");

    const onSubmit = (e) => {
        e.preventDefault();

        if (answer) {
            return onSave({
                ...answer,
                text: text
            });
        }

        return onSave({
            text: text
        });
    };

    return (
        <form className="AnswerForm" onSubmit={onSubmit}>
            <div className="control">
                <label htmlFor="answer">Answer:</label>
                <input
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    name="asnwer"
                    id="answer"
                />
            </div>
            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {answer ? "Update Answer" : "Create Answer"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default AnswerForm;
