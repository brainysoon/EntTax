  %% Initialization
clear ; close all; clc

% Load Tax_Out and Month data
data=load("/var/ml/tax_out_month.txt");
taxOut=data(:,1);
month=data(:,2);

% useful variable
m=length(taxOut)

% plot data
plot(month,taxOut,'rx',1);     % Plot the data
ylabel('Tax Out 0-1');        % Set the y-axis label
xlabel('Month 1-8');       % Set the x-axis label

pause;

%定义数据
X=[ones(m,1),data(:,2)];
theta=zeros(2,1);

iterations=1500;
alpha=0.01;

J=computeCost(X,taxOut,theta)

theta=gradientDescent(X,taxOut,theta,alpha,iterations);

%绘制预测
hold on;
plot(X(:,2),X*theta,'-');
