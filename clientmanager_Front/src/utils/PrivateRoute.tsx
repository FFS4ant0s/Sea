import React, { ReactNode } from 'react';
import { Navigate } from 'react-router-dom';

interface Props {
  children: ReactNode;
}

const PrivateRoute = ({ children }: Props) => {
  const user = localStorage.getItem('user');
  const pass = localStorage.getItem('pass');

  if (!user || !pass) {
    return <Navigate to="/" replace />;
  }

  return <>{children}</>;
};

export default PrivateRoute;