import { useParams, useLocation } from 'react-router';
import NewsList from './NewsList';
import { useState, useEffect } from 'react';

// Request Parameter
const RequestNewsPage = () => {
  console.log("Create RequestNewsPage");

  const [newsList, setNewsList] = useState([]);

  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);  // 1) => key1=value1&key2=value2&...
  const category = searchParams.get('category');              // 2) => value of key

  const getNewsData = async (query) => {
    const API_KEY = "9f5baf7d9f3f42879a20d7d19d9886e4";
    const url = `https://newsapi.org/v2/top-headlines?country=us&apiKey=${API_KEY}&${query}`;
    const response = await fetch(url);
    const newsData = await response.json();

    setNewsList(newsData.articles);
  }

  useEffect(() => {
    const query = searchParams;               // 1) using searchParams (using all query keys)
    // const query = `category=${category}`; // 2) using searchParams.get() value (using only one key)
    getNewsData(query);
  }, []);

  return (
    <>
      <NewsList newsList={newsList} />
    </>
  );
};

export default RequestNewsPage;
