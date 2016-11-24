// Let's create a "real-time search" component

var SearchExample = React.createClass({

    getInitialState: function(){
        return { searchString: '' };
    },

    handleChange: function(e){

        // If you comment out this line, the text box will not change its value.
        // This is because in React, an input cannot change independently of the value
        // that was assigned to it. In our case this is this.state.searchString.

        this.setState({searchString:e.target.value});
    },

    render: function() {

        var libraries = this.props.items,
            searchString = this.state.searchString.trim().toLowerCase();


        if(searchString.length > 0){

            // We are searching. Filter the results.

            libraries = libraries.filter(function(l){
                return l.name.toLowerCase().match( searchString );
            });

        }

        return <div>
                    <input type="text" value={this.state.searchString} onChange={this.handleChange} placeholder="Type here" />

                    <ul>

                        { libraries.map(function(l){
                            return <li>{l.name} <a href={l.url}>{l.url}</a></li>
                        }) }

                    </ul>

                </div>;

    }
});


var libraries = [

    { name: 'Backbone.js', url: 'http://documentcloud.github.io/backbone/'},
    { name: 'AngularJS', url: 'https://angularjs.org/'},
    { name: 'jQuery', url: 'http://jquery.com/'},
    { name: 'Prototype', url: 'http://www.prototypejs.org/'},
    { name: 'React', url: 'http://facebook.github.io/react/'},
    { name: 'Ember', url: 'http://emberjs.com/'},
    { name: 'Knockout.js', url: 'http://knockoutjs.com/'},
    { name: 'Dojo', url: 'http://dojotoolkit.org/'},
    { name: 'Mootools', url: 'http://mootools.net/'},
    { name: 'Underscore', url: 'http://documentcloud.github.io/underscore/'},
    { name: 'Lodash', url: 'http://lodash.com/'},
    { name: 'Moment', url: 'http://momentjs.com/'},
    { name: 'Express', url: 'http://expressjs.com/'},
    { name: 'Koa', url: 'http://koajs.com/'},

];



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
          <input className="w3-input w3-border w3-sand" ref="receiverInput" type="text"/>
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

  componentDidMount() {
    this.loadData()
  }

  addMessage(receiver, message) {
    var newMess = {sender : "test", receiver, message, date : new Date()};
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
  <SearchExample items={ libraries } />,
  <MessApp title="Messenger"></MessApp>
  </div>,
  document.getElementById('container')
)
