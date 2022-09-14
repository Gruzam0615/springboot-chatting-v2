import { useState, useEffect } from "react";

export const UseTitle = (initTitle) => {
    const [title, setTitle ] = useState(initTitle);
    const updateTitle = () => {
        const setTitle = document.querySelector("title");
        setTitle.innerText = title;
    };

    useEffect(updateTitle, [title]);
    return setTitle;
};