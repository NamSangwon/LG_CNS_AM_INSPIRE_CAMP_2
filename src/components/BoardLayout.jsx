import { Outlet } from "react-router-dom";

const BoardLayout = () => {

    return (
        <Outlet /> // BoardLayout Route 하위의 Route Element 값 출력
    );
}

export default BoardLayout;