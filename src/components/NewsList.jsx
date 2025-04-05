import NewsItem from './NewsItem';
import styles from './NewsList.module.css';

const NewsList = ({newsList}) => {
  console.log("Create NewsList");

  return (
    <div className={styles.block}>
      {
        newsList.map((news) => {
          return <NewsItem newsData={news}/>
        })
      }
    </div>
  );
};

export default NewsList;
