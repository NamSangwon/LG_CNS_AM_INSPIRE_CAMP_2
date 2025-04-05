import styles from './NewsItem.module.css';

const NewsItem = ({newsData}) => {
  console.log("Create NewsItem");

  const nullImgUrl = "https://blog.kakaocdn.net/dn/QCLyj/btsi3bv5RJH/QR7gbM99lOAT0YtCk4kONK/img.png";

  return (
    <div className={styles.block}>
      <div className={styles.thumbnail}>
        <a href={newsData.url} target="_blank">
          <img src={newsData.urlToImage || nullImgUrl}
               alt="thumbnail" />
        </a>
      </div>
      <div className={styles.contents}>
        <h2>
          <a href={newsData.url} target="_blank">{newsData.title}</a>
        </h2>
        <p>{newsData.description}</p>
      </div>
    </div>
  );
};

export default NewsItem;
