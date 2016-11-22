class MessageNew extends React.Component {
  render() {
    var addMess = event => {
      event.preventDefault();
      this.props.onAdd(
        this.refs.nameInput.value,
        this.refs.messageInput.value
    );
    this.refs.nameInput.value = "";
    this.refs.messageInput.value = "";
    }
  return (
    <form>
      <input type="text" placeholder="Name..." ref="nameInput"/><br/>
      <input type="text" placeholder="Message..." ref="messageInput"/><br/>
      <input type="reset" value="Reset" />
      <input type="submit" value="Submit" onClick={addMess}/>
    </form>
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

  addMessage(name, message) {
    var newMess = {name, message};
    this.setState({
      mess: [...this.state.mess, newMess]
    })
  }

  render(){
    var lesMessages = (this.state.mess.map(listemess => (<li key={listemess.name} >{listemess.name} : {listemess.message}</li>)));
    return (
      <div>
      <h1>Welcome to the {this.props.title} of UJM</h1>
      <hr/>
      <ul>
      {lesMessages}
      </ul>
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
