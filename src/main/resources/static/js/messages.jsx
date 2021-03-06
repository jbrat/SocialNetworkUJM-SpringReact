/*
 * Class MessageNew extends React Component to create a new message.
 */
class MessageNew extends React.Component {

  render() {
    var addMess = event => {
        event.preventDefault();
        this.props.onAdd(
            this.refs.receiverInput.value,
            this.refs.messageInput.value
        );

        this.refs.receiverInput.value = "";
        this.refs.messageInput.value = "";
    }
    return (
        <div className="w3-card-4">
            <div className="w3-container w3-brown">
            <h2>Messenger</h2>
            </div>
            <form method="post" action="/api/messages" className="w3-container">
                <p>
                    <label className="w3-label w3-text-brown"><b>Receiver</b></label>
                    <input className="w3-input w3-border w3-sand foo-things" ref="receiverInput" type="email" placeholder="Email..."  required="required" />
                    <span className="redMessage" style={{visibility: "hidden"}}>Not Empty</span>
                </p>
                <p>
                    <label className="w3-label w3-text-brown"><b>Message</b></label>
                    <input className="w3-input w3-border w3-sand" type="text" ref="messageInput" placeholder="Messages..."/>
                </p>
                <p>
                    <bouton type="reset" className="w3-btn w3-blue">Reset</bouton>
                    <bouton type="submit" className="w3-btn w3-green btnright" onClick={addMess}>Send</bouton>
                </p>
            </form>
        </div>
    )
  }
}

/*
 * Class MessAppReceiver extends React Component to manage the all Mesenger.
 */
class MessAppReceiver extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            mess: []
        };
    }

    // Function delete to get the messages from the restAPI

    delete(listemess){
      superagent
          .delete(listemess._links.self.href)
          .end( (err, response) => {
            const newState = this.state.mess;
            if (newState.indexOf(listemess) > -1) {
              newState.splice(newState.indexOf(listemess), 1);
              this.setState({mess: newState})
            }
          });
    }

    /*
     * Function loadData to ge the messages from the restAPI
     */
    loadData() {
        superagent
            .get('/getCurrentUser')
            .end( function(e, res) {
                var user = (res.body);
                var useremail = user.email;

                superagent
                    .get('/api/messages/search/findMessageByReceiver?receiver='+useremail)
                    .end( function(err, response) {
                        if (err == null) {
                            this.setState({mess: response.body._embedded.messages});
                        }
                    }.bind(this));
            }.bind(this));
    }


    /*
     * Function componentDidMount which called before load the component react, this function is used to set datas
     */
    componentDidMount() {
        this.loadData()
    }

    /*
     *
     * function to add a message.
     *
     */
    addMessage(receiver, message) {

        superagent
            .get('/getCurrentUser')
            .end( function(e, res) {
                var useremail = res.body.email;
                var newMess = {
                    sender : useremail,
                    receiver : receiver,
                    message : message,
                    date : new Date()
                };

                superagent
                    .post('/api/messages')
                    .set('Content-Type', 'application/json; charset=utf-8')
                    .send(JSON.stringify(newMess))
                    .end( function(err, response) {
                        if (err == null) {
                            super.setState({
                                mess: [...this.state.mess, newMess]
                            });
                        }
                    }.bind(this));
            }.bind(this));
    }


    /*
     * Function render of MessApp.
     */
    render() {
        var SenderReceiverMesssageDate = (this.state.mess.map(listemess => (
            <tr key={listemess.id_message} >
                <td>
                    {listemess.sender}
                </td>
                <td>
                    {listemess.receiver}
                </td>
                <td>
                    {listemess.message}
                </td>
                <td>
                    {listemess.date}
                </td>
                <td>
                  <button className="btn btn-danger" onClick={this.delete.bind(this, listemess)}>Delete</button>
                </td>
            </tr>
        )));

        return (
            <div>
                <h1 className="react w3-brown">Welcome to the {this.props.title} of UJM</h1>
                <hr/>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Sender</th>
                            <th>Receiver</th>
                            <th>Message</th>
                            <th>Date</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Sender</th>
                            <th>Receiver</th>
                            <th>Message</th>
                            <th>Date</th>
                            <th>Delete</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        {SenderReceiverMesssageDate}
                    </tbody>
                </table>
                <MessageNew onAdd={ this.addMessage.bind(this) }></MessageNew>
                <hr/>
            </div>
        )
    }
}


/*
 * Class MessAppSender extends React Component to manage the all Mesenger sender.
 */
class MessAppSender extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            mess: []
        };
    }

    // Function delete to get the messages from the restAPI

    delete(listemess){
      superagent
          .delete(listemess._links.self.href)
          .end( (err, response) => {
            const newState = this.state.mess;
            if (newState.indexOf(listemess) > -1) {
              newState.splice(newState.indexOf(listemess), 1);
              this.setState({mess: newState})
            }
          });
    }

    /*
     * Function loadData to ge the messages from the restAPI
     */
    loadData() {
        superagent
            .get('/getCurrentUser')
            .end( function(e, res) {
                var user = (res.body);
                var useremail = user.email;

                superagent
                    .get('/api/messages/search/findMessageBySender?sender='+useremail)
                    .end( function(err, response) {
                        if (err == null) {
                            this.setState({mess: response.body._embedded.messages});
                        }
                    }.bind(this));
            }.bind(this));
    }


    /*
     * Function componentDidMount which called before load the component react, this function is used to set datas
     */
    componentDidMount() {
        this.loadData()
    }
    /*
     * Function render of MessApp.
     */
    render() {
        var SenderReceiverMesssageDate = (this.state.mess.map(listemess => (
            <tr key={listemess.id_message} >
                <td>
                    {listemess.sender}
                </td>
                <td>
                    {listemess.receiver}
                </td>
                <td>
                    {listemess.message}
                </td>
                <td>
                    {listemess.date}
                </td>
                <td>
                    <button className="btn btn-danger" onClick={this.delete.bind(this, listemess)}>Delete</button>
                </td>
            </tr>
        )));

        return (
            <div>
                <h1 className="react w3-brown">Welcome to the {this.props.title} of UJM</h1>
                <hr/>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Sender</th>
                            <th>Receiver</th>
                            <th>Message</th>
                            <th>Date</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Sender</th>
                            <th>Receiver</th>
                            <th>Message</th>
                            <th>Date</th>
                            <th>Delete</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        {SenderReceiverMesssageDate}
                    </tbody>
                </table>
                <hr/>
            </div>
        )
    }
}

/*
 * Main ReactDOM render to manage the all Mesenger.
 */
ReactDOM.render(
  <div>
    <MessAppSender title="Sender Messenger"></MessAppSender>
    <MessAppReceiver title="Receiver Messenger"></MessAppReceiver>
  </div>, document.getElementById('container')
)
