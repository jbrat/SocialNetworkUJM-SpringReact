var userlist = React.createClass({
  render() {
      return (
          <div className='User'>
              <h3> Online Users </h3>
              <ul>
                  {
                      this.props.users.map((user, i) => {
                          return (
                              <li key={i}>
                                  {user}
                              </li>
                          );
                      })
                  }
              </ul>
          </div>
      );
  }
});
