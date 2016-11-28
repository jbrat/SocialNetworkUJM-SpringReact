/*
 *
 * Class MessageNew extends React Component to create a new message.
 *
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
          <input className="w3-input w3-border w3-sand" type="text"ref="messageInput" placeholder="Messages..."/>
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

/**
  *
  * Class Message React for Delete
  *
  */
class Message extends React.Component {

  render() {
    var v = this.props.v;
    var rm = event => this.props.onDel(v);
    return (
      <div>
      {this.props.v} {this.props.vsender} {this.props.vreceiver} {this.props.vmessage} {this.props.vdate}
      <button className="btn btn-danger" onClick={rm}>Delete</button>
      </div>
      )
  }
}

/*
 *
 * Class MessApp extends React Component to manage the all Mesenger.
 *
 */

class MessApp extends React.Component {
  /*
   *
   * Constructor
   *
   */
   constructor(props) {
    super(props);
    this.state = {
      mess: [
      ]
    };
  }

  /*
   *
   * function loadData to have messages.
   *
   */
  loadData(){
    superagent
      .get('/api/messages') // not HATEOS :(
      .end( (err, response) => {
          if (err == null) {
            this.setState({mess: response.body._embedded.messages});
          }
      })
  }


  /*
   *
   * function componentDidMount to load function.
   *
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
      .get('/getCurrentUser') // not HATEOS :(
      .end( function(e, res) {
        console.log('Response ok:', res.ok);
        console.log('Response text:', res.text);
        var user = (res.body);
        var useremail = user.email;
        var newMess = {sender : useremail, receiver : receiver, message : message, date : new Date()};
        console.log(newMess);
        superagent
          .post('/api/messages') // not HATEOS :(
          .set('Content-Type', 'application/json')
          .send(newMess)
          .end( function(err, response) {
            if (err == null) {
              super.setState({
                mess: [...this.state.mess, newMess]
              });
            }
          }.bind(this));

      })

  }

  deleteMessage(v){
    alert("ICI"+this);
    superagent
    .delete(v._links.self.href) // HATEOS!
    .end( (err, response) => {
      this.setState({
        mess : [this.state.mess.filter(i => i!=v)]
      })
    });
  }

  /*
   *
   * function render of MessApp.
   *
   */
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
      <td>
      <Message v={listemess.id_message} vsender={listemess.sender} vreceiver={listemess.receiver} vmessage={listemess.message} vdate={listemess.date}  onDel={ this.deleteMessage.bind(this) }></Message>
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
                <th>
                  Delete
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
                <th>
                  Delete
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



/*
 *
 * Main ReactDOM render to manage the all Mesenger.
 *
 */
ReactDOM.render(
  <div>
  <MessApp title="Messenger"></MessApp>
  </div>,
  document.getElementById('container')
)
