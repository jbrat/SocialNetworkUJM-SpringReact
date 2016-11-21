var message = React.createClass({
  render() {
      return (
          <div className="Message">
              <strong>{this.props.user} :</strong>
              <span>{this.props.text}</span>
          </div>
      );
  }
});
