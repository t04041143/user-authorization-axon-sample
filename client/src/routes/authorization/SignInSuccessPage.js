import React from "react";
import { connect } from "dva";
import DocumentTitle from "react-document-title";
import { Col, Row, Table } from "antd";

@connect(
  (state) => ({ ...state.userSession, user: state.user })
)
export default class SignInSuccessPage extends React.Component {
  componentDidMount() {
    this.props.dispatch({ type: "user/getUserDetails" });
  }

  render() {
    // let accountList = "";
    // if (this.props.user != null && this.props.user.accountList != null) {
    //   this.props.user.accountList.forEach((account) => {
    //     accountList += <>
    //       });
    //       }

    return (
      <DocumentTitle title='登录已成功'>
        <Row>
          <Col lg={6} md={6} sm={0}/>
          <Col lg={12} md={12} sm={24}>
            <Row>
              <Col md={6}/>
              <Col md={14}>
                {`已登录成功`}
              </Col>
            </Row>
            <Row>
              <Col md={6}/>
              <Col md={3}>
                用户ID
              </Col>
              <Col md={13}>
                {this.props.user.id}
              </Col>
            </Row>
            <Row>
              <Col md={6}/>
              <Col md={3}>
                绑定的账号
              </Col>
              <Col md={13}>
                <Table dataSource={this.props.user.accountList} pagination={false} columns={[{
                  title: "类型",
                  dataIndex: "type",
                  key: "type"
                }, {
                  title: "账号名",
                  dataIndex: "id",
                  key: "id"
                }]}/>
              </Col>
            </Row>
          </Col>
          <Col lg={6} md={6} sm={0}/>
        </Row>
      </DocumentTitle>
    );
  }
};
