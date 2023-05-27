import { useState } from "react";

interface Props {
  submit: (login: string, password: string) => void
}

export default function Login( submit: Props) {


  const [login, setLogin] = useState("")
  const [password, setPassword] = useState("");

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    submit.submit(login, password)
  }

  return (  
    <form onSubmit={handleSubmit} className="col-lg-5 col-md-8">
            <div className="row mt-2">
              <input
                value={login} onChange={(e) => setLogin(e.target.value)}  
                type="text" className="rounded-2" placeholder="login"  />
            </div>
            <div className="row mt-2">
              <input value={password} onChange={(e) => setPassword(e.target.value)}
              type="password" className="rounded-2" placeholder="password" />
            </div>
            <div className="row mt-2">
              <button className="btn btn-primary">
                Log in
              </button>
            </div>
          </form>
  );

}