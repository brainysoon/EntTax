function [theta,jHistory]=gradientDescent(X,y,theta,alpha,numIters)

%准备数据
m=length(y);
jHistory=zeros(numIters,1);

for iter=1:numIters

  tmp=(X*theta-y);

  theta=theta-alpha*(X'*tmp)/m;

  theta

  %保存J
  jHistory(iter)=computeCost(X,y,theta);

end

end
