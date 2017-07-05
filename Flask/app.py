from flask import Flask,render_template,request,redirect,url_for
app=Flask(__name__)

@app.route('/')
def index():
	data="Python code data..."
	return render_template("index.html",pdata=data)

@app.route('/send',methods=['GET','POST'])
def send():
	if request.method=="POST":
		return render_template("age.html",a=request.form['age'])
	return render_template("index.html")

@app.route('/age',methods=['GET','POST'])
def age():
	if request.method == "POST":
		a=request.form['age']
		return redirect(url_for('sample',variable=a))
	
	return render_template("index.html")
		
@app.route('/sample/<variable>')
def sample(variable):
	if variable<18:
		return "Your are not allowed to vote .."
	else:
		return "Your are allowed to vote .."


@app.route('/staticfile')
def staticfile():
	return render_template("staticfile.html")


if __name__ == "__main__":
	app.debug = True
	app.run()
	app.run(debug = True)