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
        <form method="post" action="/messages" className="w3-container">
          <p>
          <label className="w3-label w3-text-brown"><b>Receiver</b></label>
          <input className="w3-input w3-border w3-sand" ref="receiverInput" type="email"/>
          </p>
          <p>
          <label className="w3-label w3-text-brown"><b>Message</b></label>
          <input className="w3-input w3-border w3-sand" ref="messageInput" type="text"/>
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


class MessApp extends React.Component {

   constructor(props) {
    super(props);
    this.state = {
      mess: [
      ]
    };
  }

  loadData(){
    superagent
      .get('/api/messages') // not HATEOS :(
      .end( (err, response) => {
          if (err == null) {
            this.setState({mess: response.body._embedded.messages});
          }
      })
  }

  loadDataUser(){
    superagent
      .get('/getCurrentUser') // not HATEOS :(
      .end( function(e, res) {
        var user = (res.body);
        alert(user.email);
      })
  }
  componentDidMount() {
    this.loadData()
    this.loadDataUser()
  }

  addMessage(receiver, message) {
    var newMess = {sender : user.email, receiver, message, date : new Date()};
    superagent
      .post('/api/messages') // not HATEOS :(
      .set('Content-Type', 'application/json')
      .send(newMess)
      .end( function(err, response) {
        if (err == null) {
          this.setState({
            mess: [...this.state.mess, newMess]
          });
        }
      }.bind(this));
  }

  render(){
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
      </tr>
      )));
    return (
      <div>
      <h1 className="react w3-brown">Welcome to the {this.props.title} of UJM</h1>
      <hr/>
      <table className="table table-hover">
        <thead>
            <tr>
                <th>
                  Sender
                </th>
                <th>
                  Receiver
                </th>
                <th>
                  Message
                </th>
                <th>
                  Date
                </th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>
                  Sender
                </th>
                <th>
                  Receiver
                </th>
                <th>
                  Message
                </th>
                <th>
                  Date
                </th>
            </tr>
        </tfoot>
        <tbody>
          {SenderReceiverMesssageDate}
        </tbody>
        </table>
      <hr/>
      <MessageNew onAdd={ this.addMessage.bind(this) }></MessageNew>
      </div>
    )

  }
}

ReactDOM.render(
  <div>
  <MessApp title="Messenger"></MessApp>
  </div>,
  document.getElementById('container')
)
