from flask import Flask, render_template, request, make_response, session, url_for
app=Flask(__name__)
app.secret_key='vipin'

@app.route('/')
def index():
	return render_template("send_data/index.html")

@app.route('/getData',methods=['POST','GET'])
def getData():
	if request.method == 'POST':
		data=request.form
		return render_template("send_data/result.html", data=data)


@app.route('/setCookies')
def setCookies():
	return render_template("send_data/setCookies.html")

@app.route('/setCookiesData', methods=['POST','GET'])
def setCookiesData():
	if request.method=='POST':
		data1=request.form['data1']
		data2=request.form['data2']
		c=make_response(render_template('send_data/setCookies.html'))
		c.set_cookie('data1',data1)
		c.set_cookie('data2',data2)
		return c

@app.route('/showCookies')
def showCookies():
	c1=request.cookies.get('data1')
	c2=request.cookies.get('data2')
	return '<h1> Welcome : '+c1+'  </h1><br><br> <h2>'+c2+'</h2>'


@app.route('/setSession')
def setSession():
	if 'username' in session:
		un=session['username']
		return render_template("send_data/HomePage.html",user=un)
	return render_template("send_data/setSession.html")

#@app.route('/setSessionData1')
#def setSessionData():
#	return render_template("send_data/setSession.html")

@app.route('/setSessionData', methods=['POST','GET'])
def setSessionData2():
	if request.method=='POST':
		un=request.form['username']
		ps=request.form['password']
		session['username']=un
		session['password']=ps
	return render_template("send_data/HomePage.html",user=session['username'])

@app.route('/logout')
def logout():
	session.pop('username', None)
	return render_template("send_data/index.html")


if __name__ == "__main__":
	app.run(debug=True)