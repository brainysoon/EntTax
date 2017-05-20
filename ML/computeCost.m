function J=computeCost(X,y,theta)

m=length(y);

J=0;

%计算
h=X*theta;
tmpJ=(h-y);
J=sum(tmpJ.^2)/(2*m);

end
