import { useState } from 'react';
import { createUser } from '../api/user_api';

const Signup = () => {
    const [email, setEmail] = useState("");
    const [pwd, setPwd] = useState("");
    const [name, setName] = useState("");

    return (
        <div class="container mt-5">
            <div class="row">
                <form method="post">
                    <div class="mb-3">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email" name="email" 
                            value={email} onChange={(e)=>{
                                setEmail(e.target.value);
                            }}/>
                    </div>
                    <div class="mb-3">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" name="pwd" 
                            value={pwd} onChange={(e)=>{
                                setPwd(e.target.value);
                            }}/>
                    </div>
                    <div class="mb-3">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" 
                            value={name} onChange={(e)=>{
                                setName(e.target.value);
                            }}/>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary" id="signup" 
                            onClick={async (e)=>{
                                e.preventDefault();
                                const obj = {email, pwd, name}; // 변수명과 키 명이 같으면, 자동 매핑
                                const result = await createUser(JSON.stringify(obj));
                                alert(result.message);
                            }}>Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Signup;