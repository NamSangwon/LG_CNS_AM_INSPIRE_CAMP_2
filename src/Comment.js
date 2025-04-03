// Apply CSS (using by module)
import styles from './App.module.css'

function UserInfo(props) {
    return (
      <div className="user-info">
        <img className="avatar"
          src={props.author.avatarUrl}
          alt={props.author.name}
        />
        <div className="user-info-name">
          {props.author.name}
        </div>
      </div>
    );
  }
  
  function Comment(props) {
    return (
      <div className="comment">
        <UserInfo author={props.author} />
        <div className="comment-text" style = {
          {
            "font-size" : "2rem",
            "background-color" : "yellow"
          }
        }>
          {props.text}
        </div>
        <div className={styles.date}>
          {props.date}
        </div>
      </div>
    )
  }

  export default Comment;