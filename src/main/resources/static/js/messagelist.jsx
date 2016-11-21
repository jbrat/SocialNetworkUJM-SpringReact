var messagelist = React.createClass({
render() {
    return (
        <div className='Message'>
            <h2> Conversation: </h2>
            {
                this.props.Message.map((message, i) => {
                    return (
                        <message
                            key={i}
                            user={message.user}
                            text={message.text}
                        />
                    );
                })
            }
        </div>
    );
}
});
