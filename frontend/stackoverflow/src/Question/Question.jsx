import {useParams} from "react-router-dom";
import {useState} from "react";

const Question = () => {
    const params = useParams();
    const[question, setQuestion] = useState();

    return (<div className={"Question"}>

    </div>);
}