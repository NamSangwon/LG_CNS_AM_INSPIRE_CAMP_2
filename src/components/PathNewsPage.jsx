import { useParams, useLocation } from 'react-router';
import NewsList from './NewsList';
import { useState, useEffect } from 'react';

  // Path Parameter
const PathNewsPage = () => {
  console.log("Create PathNewsPage");

  const [newsList, setNewsList] = useState([]);

  const param = useParams();
  const path = param['*'] || ''; // null == all articles

  const getNewsData = async (category) => {
    const API_KEY = "";
    const url = `https://newsapi.org/v2/top-headlines?country=us&apiKey=${API_KEY}&category=${category}`;
    const response = await fetch(url);
    const newsData = await response.json();

    setNewsList(newsData.articles);
  }

  useEffect(() => {
    getNewsData(path);
  }, []);

  return (
    <>
      <NewsList newsList={newsList} />
    </>
  );
};

export default PathNewsPage;
